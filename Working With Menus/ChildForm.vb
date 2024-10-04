Imports System.Windows.Forms.VisualStyles.VisualStyleElement.Window

Public Class ChildForm

    'TRANSFER
    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        Label1.Text = TextBox1.Text
    End Sub

    Private Sub ToolStripButton1_Click(sender As Object, e As EventArgs) Handles ToolStripButton1.Click
        Dim fontDialog As New FontDialog()

        ' Show the FontDialog and get the selected font
        If fontDialog.ShowDialog() = DialogResult.OK Then
            Label1.Font = fontDialog.Font
        End If
    End Sub

    Private Sub ToolStripButton2_Click(sender As Object, e As EventArgs) Handles ToolStripButton2.Click
        Dim colorDialog As New ColorDialog()

        ' Show the ColorDialog and get the selected color
        If colorDialog.ShowDialog() = DialogResult.OK Then
            Label1.ForeColor = colorDialog.Color
        End If
    End Sub

    Private Sub DateToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles DateToolStripMenuItem.Click
        Label3.Text = DateString
    End Sub

    Private Sub TimeToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles TimeToolStripMenuItem.Click
        Label3.Text = TimeString
    End Sub
End Class