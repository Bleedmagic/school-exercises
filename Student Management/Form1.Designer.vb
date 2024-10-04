<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()>
Partial Class Form1
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()>
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()>
    Private Sub InitializeComponent()
        txtName = New TextBox()
        txtAge = New TextBox()
        txtGrade = New TextBox()
        lblStudentId = New Label()
        lblName = New Label()
        lblAge = New Label()
        lblGrade = New Label()
        btnAdd = New Button()
        btnEdit = New Button()
        btnDelete = New Button()
        dgvStudents = New DataGridView()
        lblStudentIdNum = New Label()
        btnCancel = New Button()
        btnSave = New Button()
        lblTitle = New Label()
        btnRefresh = New Button()
        CType(dgvStudents, ComponentModel.ISupportInitialize).BeginInit()
        SuspendLayout()
        ' 
        ' txtName
        ' 
        txtName.Location = New Point(133, 131)
        txtName.Name = "txtName"
        txtName.Size = New Size(140, 24)
        txtName.TabIndex = 1
        ' 
        ' txtAge
        ' 
        txtAge.Location = New Point(133, 165)
        txtAge.Name = "txtAge"
        txtAge.Size = New Size(140, 24)
        txtAge.TabIndex = 2
        ' 
        ' txtGrade
        ' 
        txtGrade.Location = New Point(133, 200)
        txtGrade.Name = "txtGrade"
        txtGrade.Size = New Size(140, 24)
        txtGrade.TabIndex = 3
        ' 
        ' lblStudentId
        ' 
        lblStudentId.AutoSize = True
        lblStudentId.Location = New Point(34, 97)
        lblStudentId.Name = "lblStudentId"
        lblStudentId.Size = New Size(80, 18)
        lblStudentId.TabIndex = 4
        lblStudentId.Text = "Student ID:"
        ' 
        ' lblName
        ' 
        lblName.AutoSize = True
        lblName.Location = New Point(68, 131)
        lblName.Name = "lblName"
        lblName.Size = New Size(52, 18)
        lblName.TabIndex = 5
        lblName.Text = "Name:"
        ' 
        ' lblAge
        ' 
        lblAge.AutoSize = True
        lblAge.Location = New Point(82, 168)
        lblAge.Name = "lblAge"
        lblAge.Size = New Size(37, 18)
        lblAge.TabIndex = 6
        lblAge.Text = "Age:"
        ' 
        ' lblGrade
        ' 
        lblGrade.AutoSize = True
        lblGrade.Location = New Point(68, 200)
        lblGrade.Name = "lblGrade"
        lblGrade.Size = New Size(53, 18)
        lblGrade.TabIndex = 7
        lblGrade.Text = "Grade:"
        ' 
        ' btnAdd
        ' 
        btnAdd.Cursor = Cursors.Hand
        btnAdd.Location = New Point(364, 103)
        btnAdd.Name = "btnAdd"
        btnAdd.Size = New Size(106, 26)
        btnAdd.TabIndex = 8
        btnAdd.Text = "Add"
        btnAdd.UseVisualStyleBackColor = True
        ' 
        ' btnEdit
        ' 
        btnEdit.Cursor = Cursors.Hand
        btnEdit.Enabled = False
        btnEdit.Location = New Point(500, 103)
        btnEdit.Name = "btnEdit"
        btnEdit.Size = New Size(106, 26)
        btnEdit.TabIndex = 9
        btnEdit.Text = "Edit"
        btnEdit.UseVisualStyleBackColor = True
        ' 
        ' btnDelete
        ' 
        btnDelete.Cursor = Cursors.Hand
        btnDelete.Enabled = False
        btnDelete.Location = New Point(500, 153)
        btnDelete.Name = "btnDelete"
        btnDelete.Size = New Size(106, 26)
        btnDelete.TabIndex = 10
        btnDelete.Text = "Delete"
        btnDelete.UseVisualStyleBackColor = True
        ' 
        ' dgvStudents
        ' 
        dgvStudents.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize
        dgvStudents.Location = New Point(30, 239)
        dgvStudents.Name = "dgvStudents"
        dgvStudents.RowHeadersWidth = 51
        dgvStudents.Size = New Size(576, 169)
        dgvStudents.TabIndex = 11
        ' 
        ' lblStudentIdNum
        ' 
        lblStudentIdNum.BorderStyle = BorderStyle.Fixed3D
        lblStudentIdNum.Location = New Point(133, 97)
        lblStudentIdNum.Name = "lblStudentIdNum"
        lblStudentIdNum.Size = New Size(75, 18)
        lblStudentIdNum.TabIndex = 12
        lblStudentIdNum.TextAlign = ContentAlignment.MiddleCenter
        ' 
        ' btnCancel
        ' 
        btnCancel.Cursor = Cursors.Hand
        btnCancel.Enabled = False
        btnCancel.Location = New Point(500, 200)
        btnCancel.Name = "btnCancel"
        btnCancel.Size = New Size(106, 26)
        btnCancel.TabIndex = 13
        btnCancel.Text = "Cancel"
        btnCancel.UseVisualStyleBackColor = True
        ' 
        ' btnSave
        ' 
        btnSave.Cursor = Cursors.Hand
        btnSave.Enabled = False
        btnSave.Location = New Point(364, 198)
        btnSave.Name = "btnSave"
        btnSave.Size = New Size(106, 26)
        btnSave.TabIndex = 14
        btnSave.Text = "Save"
        btnSave.UseVisualStyleBackColor = True
        ' 
        ' lblTitle
        ' 
        lblTitle.Anchor = AnchorStyles.Top Or AnchorStyles.Bottom Or AnchorStyles.Left Or AnchorStyles.Right
        lblTitle.BackColor = SystemColors.ButtonFace
        lblTitle.Font = New Font("Microsoft Sans Serif", 19.8000011F, FontStyle.Bold, GraphicsUnit.Point, CByte(0))
        lblTitle.Location = New Point(34, 13)
        lblTitle.Name = "lblTitle"
        lblTitle.Size = New Size(567, 65)
        lblTitle.TabIndex = 15
        lblTitle.Text = "Student Records"
        lblTitle.TextAlign = ContentAlignment.MiddleCenter
        ' 
        ' btnRefresh
        ' 
        btnRefresh.Cursor = Cursors.Hand
        btnRefresh.Location = New Point(364, 153)
        btnRefresh.Name = "btnRefresh"
        btnRefresh.Size = New Size(106, 26)
        btnRefresh.TabIndex = 16
        btnRefresh.Text = "Refresh"
        btnRefresh.UseVisualStyleBackColor = True
        ' 
        ' Form1
        ' 
        AutoScaleDimensions = New SizeF(9F, 18F)
        AutoScaleMode = AutoScaleMode.Font
        BackColor = SystemColors.Control
        ClientSize = New Size(635, 430)
        Controls.Add(btnRefresh)
        Controls.Add(lblTitle)
        Controls.Add(btnSave)
        Controls.Add(btnCancel)
        Controls.Add(lblStudentIdNum)
        Controls.Add(dgvStudents)
        Controls.Add(btnDelete)
        Controls.Add(btnEdit)
        Controls.Add(btnAdd)
        Controls.Add(lblGrade)
        Controls.Add(lblAge)
        Controls.Add(lblName)
        Controls.Add(lblStudentId)
        Controls.Add(txtGrade)
        Controls.Add(txtAge)
        Controls.Add(txtName)
        Font = New Font("Microsoft Sans Serif", 9F, FontStyle.Regular, GraphicsUnit.Point, CByte(0))
        FormBorderStyle = FormBorderStyle.FixedSingle
        MaximizeBox = False
        MinimizeBox = False
        Name = "Form1"
        Text = "School Form"
        CType(dgvStudents, ComponentModel.ISupportInitialize).EndInit()
        ResumeLayout(False)
        PerformLayout()
    End Sub
    Friend WithEvents txtName As TextBox
    Friend WithEvents txtAge As TextBox
    Friend WithEvents txtGrade As TextBox
    Friend WithEvents lblStudentId As Label
    Friend WithEvents lblName As Label
    Friend WithEvents lblAge As Label
    Friend WithEvents lblGrade As Label
    Friend WithEvents btnAdd As Button
    Friend WithEvents btnEdit As Button
    Friend WithEvents btnDelete As Button
    Friend WithEvents dgvStudents As DataGridView
    Friend WithEvents lblStudentIdNum As Label
    Friend WithEvents btnCancel As Button
    Friend WithEvents btnSave As Button
    Friend WithEvents lblTitle As Label
    Friend WithEvents btnRefresh As Button

End Class
