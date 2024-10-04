<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class ChildForm
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
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
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(ChildForm))
        ToolStrip1 = New ToolStrip()
        ToolStripButton1 = New ToolStripButton()
        ToolStripButton2 = New ToolStripButton()
        Button1 = New Button()
        Label1 = New Label()
        TextBox1 = New TextBox()
        Label2 = New Label()
        Label3 = New Label()
        FontDialog1 = New FontDialog()
        ColorDialog1 = New ColorDialog()
        Label4 = New Label()
        MenuStrip1 = New MenuStrip()
        ClockToolStripMenuItem = New ToolStripMenuItem()
        DateToolStripMenuItem = New ToolStripMenuItem()
        TimeToolStripMenuItem = New ToolStripMenuItem()
        ToolStrip1.SuspendLayout()
        MenuStrip1.SuspendLayout()
        SuspendLayout()
        ' 
        ' ToolStrip1
        ' 
        ToolStrip1.ImageScalingSize = New Size(20, 20)
        ToolStrip1.Items.AddRange(New ToolStripItem() {ToolStripButton1, ToolStripButton2})
        ToolStrip1.Location = New Point(0, 28)
        ToolStrip1.Name = "ToolStrip1"
        ToolStrip1.Size = New Size(800, 27)
        ToolStrip1.TabIndex = 0
        ToolStrip1.Text = "ToolStrip1"
        ' 
        ' ToolStripButton1
        ' 
        ToolStripButton1.Image = CType(resources.GetObject("ToolStripButton1.Image"), Image)
        ToolStripButton1.ImageTransparentColor = Color.Magenta
        ToolStripButton1.Name = "ToolStripButton1"
        ToolStripButton1.Size = New Size(62, 24)
        ToolStripButton1.Text = "Font"
        ' 
        ' ToolStripButton2
        ' 
        ToolStripButton2.Image = CType(resources.GetObject("ToolStripButton2.Image"), Image)
        ToolStripButton2.ImageTransparentColor = Color.Magenta
        ToolStripButton2.Name = "ToolStripButton2"
        ToolStripButton2.Size = New Size(69, 24)
        ToolStripButton2.Text = "Color"
        ' 
        ' Button1
        ' 
        Button1.Location = New Point(305, 345)
        Button1.Name = "Button1"
        Button1.Size = New Size(150, 52)
        Button1.TabIndex = 1
        Button1.Text = "Display"
        Button1.UseVisualStyleBackColor = True
        ' 
        ' Label1
        ' 
        Label1.BorderStyle = BorderStyle.FixedSingle
        Label1.Font = New Font("Microsoft Sans Serif", 19.8000011F, FontStyle.Regular, GraphicsUnit.Point)
        Label1.Location = New Point(191, 212)
        Label1.Name = "Label1"
        Label1.Size = New Size(382, 113)
        Label1.TabIndex = 2
        Label1.TextAlign = ContentAlignment.MiddleCenter
        ' 
        ' TextBox1
        ' 
        TextBox1.Location = New Point(191, 173)
        TextBox1.Name = "TextBox1"
        TextBox1.Size = New Size(382, 27)
        TextBox1.TabIndex = 3
        ' 
        ' Label2
        ' 
        Label2.AutoSize = True
        Label2.Font = New Font("Microsoft Sans Serif", 10F, FontStyle.Regular, GraphicsUnit.Point)
        Label2.Location = New Point(191, 150)
        Label2.Name = "Label2"
        Label2.Size = New Size(171, 20)
        Label2.TabIndex = 4
        Label2.Text = "Enter Text to Display:"
        ' 
        ' Label3
        ' 
        Label3.BorderStyle = BorderStyle.FixedSingle
        Label3.Font = New Font("Microsoft Sans Serif", 10.2F, FontStyle.Bold, GraphicsUnit.Point)
        Label3.Location = New Point(612, 55)
        Label3.Name = "Label3"
        Label3.Size = New Size(175, 41)
        Label3.TabIndex = 5
        Label3.TextAlign = ContentAlignment.MiddleCenter
        ' 
        ' Label4
        ' 
        Label4.AutoSize = True
        Label4.Font = New Font("Microsoft Sans Serif", 10F, FontStyle.Regular, GraphicsUnit.Point)
        Label4.Location = New Point(695, 32)
        Label4.Name = "Label4"
        Label4.Size = New Size(92, 20)
        Label4.TabIndex = 6
        Label4.Text = "Date/Time:"
        ' 
        ' MenuStrip1
        ' 
        MenuStrip1.ImageScalingSize = New Size(20, 20)
        MenuStrip1.Items.AddRange(New ToolStripItem() {ClockToolStripMenuItem})
        MenuStrip1.Location = New Point(0, 0)
        MenuStrip1.Name = "MenuStrip1"
        MenuStrip1.Size = New Size(800, 28)
        MenuStrip1.TabIndex = 7
        MenuStrip1.Text = "MenuStrip1"
        ' 
        ' ClockToolStripMenuItem
        ' 
        ClockToolStripMenuItem.DropDownItems.AddRange(New ToolStripItem() {DateToolStripMenuItem, TimeToolStripMenuItem})
        ClockToolStripMenuItem.Name = "ClockToolStripMenuItem"
        ClockToolStripMenuItem.Size = New Size(59, 24)
        ClockToolStripMenuItem.Text = "&Clock"
        ' 
        ' DateToolStripMenuItem
        ' 
        DateToolStripMenuItem.Name = "DateToolStripMenuItem"
        DateToolStripMenuItem.ShortcutKeys = Keys.Control Or Keys.D
        DateToolStripMenuItem.Size = New Size(177, 26)
        DateToolStripMenuItem.Text = "&Date"
        ' 
        ' TimeToolStripMenuItem
        ' 
        TimeToolStripMenuItem.Name = "TimeToolStripMenuItem"
        TimeToolStripMenuItem.ShortcutKeys = Keys.Control Or Keys.T
        TimeToolStripMenuItem.Size = New Size(177, 26)
        TimeToolStripMenuItem.Text = "&Time"
        ' 
        ' ChildForm
        ' 
        AutoScaleDimensions = New SizeF(8F, 20F)
        AutoScaleMode = AutoScaleMode.Font
        ClientSize = New Size(800, 450)
        Controls.Add(Label4)
        Controls.Add(Label3)
        Controls.Add(Label2)
        Controls.Add(TextBox1)
        Controls.Add(Label1)
        Controls.Add(Button1)
        Controls.Add(ToolStrip1)
        Controls.Add(MenuStrip1)
        MainMenuStrip = MenuStrip1
        MaximizeBox = False
        MinimizeBox = False
        Name = "ChildForm"
        Text = "ChildForm"
        ToolStrip1.ResumeLayout(False)
        ToolStrip1.PerformLayout()
        MenuStrip1.ResumeLayout(False)
        MenuStrip1.PerformLayout()
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents ToolStrip1 As ToolStrip
    Friend WithEvents ToolStripButton1 As ToolStripButton
    Friend WithEvents ToolStripButton2 As ToolStripButton
    Friend WithEvents Button1 As Button
    Friend WithEvents Label1 As Label
    Friend WithEvents TextBox1 As TextBox
    Friend WithEvents Label2 As Label
    Friend WithEvents Label3 As Label
    Friend WithEvents FontDialog1 As FontDialog
    Friend WithEvents ColorDialog1 As ColorDialog
    Friend WithEvents Label4 As Label
    Friend WithEvents MenuStrip1 As MenuStrip
    Friend WithEvents ClockToolStripMenuItem As ToolStripMenuItem
    Friend WithEvents DateToolStripMenuItem As ToolStripMenuItem
    Friend WithEvents TimeToolStripMenuItem As ToolStripMenuItem
End Class
