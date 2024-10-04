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
        Dim resources As ComponentModel.ComponentResourceManager = New ComponentModel.ComponentResourceManager(GetType(Form1))
        labelTitle = New Label()
        labelRealTime = New Label()
        labelDelayed = New Label()
        boxRealTime = New TextBox()
        boxDelayed = New TextBox()
        buttonDisplay = New Button()
        SuspendLayout()
        ' 
        ' labelTitle
        ' 
        labelTitle.Anchor = AnchorStyles.Top Or AnchorStyles.Left Or AnchorStyles.Right
        labelTitle.Font = New Font("Microsoft Sans Serif", 18F, FontStyle.Bold, GraphicsUnit.Point)
        labelTitle.Location = New Point(0, -1)
        labelTitle.Margin = New Padding(4, 0, 4, 0)
        labelTitle.Name = "labelTitle"
        labelTitle.Size = New Size(934, 143)
        labelTitle.TabIndex = 0
        labelTitle.Text = "Ditto Copies Everything!"
        labelTitle.TextAlign = ContentAlignment.MiddleCenter
        ' 
        ' labelRealTime
        ' 
        labelRealTime.Anchor = AnchorStyles.Top Or AnchorStyles.Bottom Or AnchorStyles.Left Or AnchorStyles.Right
        labelRealTime.AutoSize = True
        labelRealTime.Font = New Font("Microsoft Sans Serif", 12F, FontStyle.Regular, GraphicsUnit.Point)
        labelRealTime.ForeColor = SystemColors.ControlText
        labelRealTime.Location = New Point(97, 168)
        labelRealTime.Margin = New Padding(4, 0, 4, 0)
        labelRealTime.Name = "labelRealTime"
        labelRealTime.Size = New Size(431, 29)
        labelRealTime.TabIndex = 1
        labelRealTime.Text = "Enter Message to Display in Real-Time"
        ' 
        ' labelDelayed
        ' 
        labelDelayed.Anchor = AnchorStyles.Top Or AnchorStyles.Bottom Or AnchorStyles.Left Or AnchorStyles.Right
        labelDelayed.AutoSize = True
        labelDelayed.Font = New Font("Microsoft Sans Serif", 12F, FontStyle.Regular, GraphicsUnit.Point)
        labelDelayed.Location = New Point(97, 309)
        labelDelayed.Margin = New Padding(4, 0, 4, 0)
        labelDelayed.Name = "labelDelayed"
        labelDelayed.Size = New Size(428, 29)
        labelDelayed.TabIndex = 2
        labelDelayed.Text = "Enter Message to Display Using Button"
        ' 
        ' boxRealTime
        ' 
        boxRealTime.Anchor = AnchorStyles.Top Or AnchorStyles.Bottom Or AnchorStyles.Left Or AnchorStyles.Right
        boxRealTime.Font = New Font("Microsoft Sans Serif", 12F, FontStyle.Regular, GraphicsUnit.Point)
        boxRealTime.Location = New Point(97, 200)
        boxRealTime.Margin = New Padding(4, 3, 4, 3)
        boxRealTime.Name = "boxRealTime"
        boxRealTime.Size = New Size(737, 35)
        boxRealTime.TabIndex = 3
        ' 
        ' boxDelayed
        ' 
        boxDelayed.Anchor = AnchorStyles.Top Or AnchorStyles.Bottom Or AnchorStyles.Left Or AnchorStyles.Right
        boxDelayed.Font = New Font("Microsoft Sans Serif", 12F, FontStyle.Regular, GraphicsUnit.Point)
        boxDelayed.Location = New Point(97, 341)
        boxDelayed.Margin = New Padding(4, 3, 4, 3)
        boxDelayed.Name = "boxDelayed"
        boxDelayed.Size = New Size(738, 35)
        boxDelayed.TabIndex = 4
        ' 
        ' buttonDisplay
        ' 
        buttonDisplay.AutoSize = True
        buttonDisplay.Location = New Point(286, 436)
        buttonDisplay.Margin = New Padding(4, 3, 4, 3)
        buttonDisplay.Name = "buttonDisplay"
        buttonDisplay.Size = New Size(348, 48)
        buttonDisplay.TabIndex = 5
        buttonDisplay.Text = "Display"
        buttonDisplay.UseVisualStyleBackColor = True
        ' 
        ' Form1
        ' 
        AutoScaleDimensions = New SizeF(12F, 25F)
        AutoScaleMode = AutoScaleMode.Font
        BackColor = SystemColors.Control
        ClientSize = New Size(934, 544)
        Controls.Add(buttonDisplay)
        Controls.Add(boxDelayed)
        Controls.Add(boxRealTime)
        Controls.Add(labelDelayed)
        Controls.Add(labelRealTime)
        Controls.Add(labelTitle)
        Font = New Font("Microsoft Sans Serif", 10F, FontStyle.Regular, GraphicsUnit.Point)
        FormBorderStyle = FormBorderStyle.FixedDialog
        Icon = CType(resources.GetObject("$this.Icon"), Icon)
        Margin = New Padding(4, 3, 4, 3)
        MaximizeBox = False
        MinimizeBox = False
        Name = "Form1"
        Text = "WinForm: Ditto"
        ResumeLayout(False)
        PerformLayout()
    End Sub

    Friend WithEvents labelTitle As Label
    Friend WithEvents labelRealTime As Label
    Friend WithEvents labelDelayed As Label
    Friend WithEvents boxRealTime As TextBox
    Friend WithEvents boxDelayed As TextBox
    Friend WithEvents buttonDisplay As Button
End Class
