Imports System.Reflection.Emit

Public Class Form1
    'Confirmation when Closing
    Private Sub Form1_FormClosing(ByVal sender As System.Object, ByVal e As System.Windows.Forms.FormClosingEventArgs) Handles MyBase.FormClosing

        Dim result As DialogResult = MessageBox.Show("Are you sure you want to exit?", "Terminate Program", MessageBoxButtons.YesNo)

        If result = DialogResult.No Then
            e.Cancel = True
        End If
    End Sub

    'CHILDFORM
    Private Sub ProjectToolStripMenuItem1_Click(sender As Object, e As EventArgs) Handles ProjectToolStripMenuItem1.Click
        Dim childForm As New ChildForm()
        childForm.MdiParent = Me
        childForm.Show()
    End Sub

    'FILE > EXIT
    Private Sub ExitToolStripMenuItem_Click(sender As Object, e As EventArgs) Handles ExitToolStripMenuItem.Click
        Application.Exit()
    End Sub
End Class
