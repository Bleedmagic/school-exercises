Imports MySql.Data.MySqlClient

Public Class Form1

    Dim connectionString As String = "server=localhost;user=root;password=;database=student_management"
    Dim connection As MySqlConnection
    Dim adapter As MySqlDataAdapter
    Dim dataTable As DataTable

    Private Sub Form1_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Populate()
    End Sub

    Private Sub Form1_FormClosing(sender As Object, e As FormClosingEventArgs) Handles Me.FormClosing
        Dim result As DialogResult = MessageBox.Show("Are you sure you want to quit?", "Confirmation", MessageBoxButtons.YesNo, MessageBoxIcon.Question)
        If result = DialogResult.No Then
            e.Cancel = True
        End If
    End Sub

    Private Sub Populate()
        Try
            connection = New MySqlConnection(connectionString)
            connection.Open()

            adapter = New MySqlDataAdapter("SELECT student_id, student_name, student_age, student_grade FROM students", connection)
            dataTable = New DataTable()
            adapter.Fill(dataTable)

            dgvStudents.DataSource = dataTable

            ResetForm()
        Catch ex As Exception
            MessageBox.Show("Error: " & ex.Message)
        Finally
            If connection IsNot Nothing AndAlso connection.State = ConnectionState.Open Then
                connection.Close()
            End If
        End Try
    End Sub


    Private Sub ResetForm()
        txtName.Enabled = True
        txtAge.Enabled = True
        txtGrade.Enabled = True
        btnAdd.Enabled = True
        btnEdit.Enabled = False
        btnDelete.Enabled = False
        btnCancel.Enabled = False
        btnSave.Enabled = False
        btnRefresh.Enabled = True

        txtName.Text = ""
        txtAge.Text = ""
        txtGrade.Text = ""
        lblStudentIdNum.Text = ""

        dgvStudents.ClearSelection()
    End Sub


    Private Sub dgvStudents_SelectionChanged(sender As Object, e As EventArgs) Handles dgvStudents.SelectionChanged
        If dgvStudents.SelectedRows.Count > 0 Then
            Dim selectedRow As DataGridViewRow = dgvStudents.SelectedRows(0)
            lblStudentIdNum.Text = selectedRow.Cells("student_id").Value.ToString()
            txtName.Text = selectedRow.Cells("student_name").Value.ToString()
            txtAge.Text = selectedRow.Cells("student_age").Value.ToString()
            txtGrade.Text = selectedRow.Cells("student_grade").Value.ToString()

            txtName.Enabled = False
            txtAge.Enabled = False
            txtGrade.Enabled = False

            btnAdd.Enabled = False
            btnEdit.Enabled = True
            btnDelete.Enabled = True
            btnCancel.Enabled = True
            btnRefresh.Enabled = False
        Else
            lblStudentIdNum.Text = ""
            txtName.Text = ""
            txtAge.Text = ""
            txtGrade.Text = ""

            txtName.Enabled = True
            txtAge.Enabled = True
            txtGrade.Enabled = True

            btnAdd.Enabled = True
            btnEdit.Enabled = False
            btnDelete.Enabled = False
            btnCancel.Enabled = False
        End If
    End Sub

    Private Function ValidateInputs() As Boolean
        If String.IsNullOrWhiteSpace(txtName.Text) Then
            MessageBox.Show("Please enter a valid name.")
            Return False
        End If

        Dim age As Integer
        If Not Integer.TryParse(txtAge.Text, age) OrElse age < 0 Then
            MessageBox.Show("Please enter a valid age.")
            Return False
        End If

        Dim grade As Decimal
        If Not Decimal.TryParse(txtGrade.Text, grade) OrElse grade < 0 OrElse grade > 100 Then
            MessageBox.Show("Please enter a valid grade between 0 and 100.")
            Return False
        End If

        Return True
    End Function

    Private Sub btnAdd_Click(sender As Object, e As EventArgs) Handles btnAdd.Click
        If ValidateInputs() Then
            Try
                connection = New MySqlConnection(connectionString)
                connection.Open()

                Dim query As String = "INSERT INTO students (student_name, student_age, student_grade) VALUES (@name, @age, @grade)"
                Dim command As MySqlCommand = New MySqlCommand(query, connection)
                command.Parameters.AddWithValue("@name", txtName.Text)
                command.Parameters.AddWithValue("@age", txtAge.Text)
                command.Parameters.AddWithValue("@grade", txtGrade.Text)
                command.ExecuteNonQuery()

                Populate()
            Catch ex As Exception
                MessageBox.Show("Error: " & ex.Message)
            Finally
                If connection IsNot Nothing AndAlso connection.State = ConnectionState.Open Then
                    connection.Close()
                End If
            End Try
        End If
    End Sub

    Private Sub btnDelete_Click(sender As Object, e As EventArgs) Handles btnDelete.Click
        If MessageBox.Show("Are you sure you want to delete this record?", "Confirmation", MessageBoxButtons.YesNo) = DialogResult.Yes Then
            Try
                connection = New MySqlConnection(connectionString)
                connection.Open()

                Dim query As String = "DELETE FROM students WHERE student_id = @id"
                Dim command As MySqlCommand = New MySqlCommand(query, connection)
                command.Parameters.AddWithValue("@id", lblStudentIdNum.Text)
                command.ExecuteNonQuery()

                Populate()
            Catch ex As Exception
                MessageBox.Show("Error: " & ex.Message)
            Finally
                If connection IsNot Nothing AndAlso connection.State = ConnectionState.Open Then
                    connection.Close()
                End If
            End Try
        End If
    End Sub

    Private Sub btnEdit_Click(sender As Object, e As EventArgs) Handles btnEdit.Click
        If dgvStudents.SelectedRows.Count > 0 Then
            txtName.Enabled = True
            txtAge.Enabled = True
            txtGrade.Enabled = True

            btnEdit.Enabled = False
            btnSave.Enabled = True
            btnDelete.Enabled = False
            btnCancel.Enabled = True
        Else
            MessageBox.Show("Please select a row to edit.")
        End If
    End Sub

    Private Sub btnSave_Click(sender As Object, e As EventArgs) Handles btnSave.Click
        If ValidateInputs() Then
            Dim result As DialogResult = MessageBox.Show("Are you sure you want to save changes?", "Confirmation", MessageBoxButtons.YesNo)
            If result = DialogResult.Yes Then
                Try
                    connection = New MySqlConnection(connectionString)
                    connection.Open()

                    Dim query As String = "UPDATE students SET student_name = @name, student_age = @age, student_grade = @grade WHERE student_id = @id"
                    Dim cmd As MySqlCommand = New MySqlCommand(query, connection)
                    cmd.Parameters.AddWithValue("@name", txtName.Text)
                    cmd.Parameters.AddWithValue("@age", Convert.ToInt32(txtAge.Text))
                    cmd.Parameters.AddWithValue("@grade", Convert.ToDecimal(txtGrade.Text))
                    cmd.Parameters.AddWithValue("@id", Convert.ToInt32(lblStudentIdNum.Text))
                    cmd.ExecuteNonQuery()
                    ResetForm()
                    Populate()
                Catch ex As Exception
                    MessageBox.Show("Error saving changes: " & ex.Message)
                Finally
                    connection.Close()
                End Try
            End If
        End If
    End Sub

    Private Sub btnCancel_Click(sender As Object, e As EventArgs) Handles btnCancel.Click
        ResetForm()
    End Sub

    Private Sub dgvStudents_EditingControlShowing(sender As Object, e As DataGridViewEditingControlShowingEventArgs) Handles dgvStudents.EditingControlShowing
        If TypeOf e.Control Is TextBox Then
            Dim textBox As TextBox = CType(e.Control, TextBox)
            textBox.ReadOnly = True
        End If
    End Sub

    Private Sub btnRefresh_Click(sender As Object, e As EventArgs) Handles btnRefresh.Click
        Populate()
    End Sub

End Class
