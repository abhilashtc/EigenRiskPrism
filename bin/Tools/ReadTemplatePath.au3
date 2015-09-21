#region --- Au3Recorder generated code Start (v3.3.9.5 KeyboardLayout=00004009)  ---

#region --- Internal functions Au3Recorder Start ---
Func _Au3RecordSetup()
Opt('WinWaitDelay',100)
Opt('WinDetectHiddenText',1)
Opt('MouseCoordMode',0)
Local $aResult = DllCall('User32.dll', 'int', 'GetKeyboardLayoutNameW', 'wstr', '')
If $aResult[1] <> '00004009' Then
  ;MsgBox(64, 'Warning', 'Recording has been done under a different Keyboard layout' & @CRLF & '(00004009->' & $aResult[1] & ')')
EndIf

EndFunc

Func _WinWaitActivate($title,$text,$timeout=0)
	WinWait($title,$text,$timeout)
	If Not WinActive($title,$text) Then WinActivate($title,$text)
	WinWaitActive($title,$text,$timeout)
EndFunc

_AU3RecordSetup()
#endregion --- Internal functions Au3Recorder End ---

#include <FileConstants.au3>
#include <MsgBoxConstants.au3>

ReadExposurePath()

_WinWaitActivate("Enter name of file to save to…","")
MouseClick("left",428,381,1)
;Send("{CTRLDOWN}a{CTRLUP}{DEL}{CTRLDOWN}v{CTRLUP}{ENTER}")
Send("{CTRLDOWN}a{CTRLUP}")
Send("{DEL}")
Send("{CTRLDOWN}")
Send("v{CTRLUP}{ENTER}")


Func ReadExposurePath()
   ;MsgBox($MB_SYSTEMMODAL, "Title", "Inside Example.", 10)
   ;Create a constant variable in Local scope of the filepath that will be read/written to.
    Local Const $sFilePath = "C:\Temp_Path\TemplateDetails.txt"

    ; Create a temporary file to read data from.
    If Not FileCreate($sFilePath, "This is an example of using FileReadLine.") Then Return MsgBox($MB_SYSTEMMODAL, "", "An error occurred whilst writing the temporary file.")

    ; Open the file for reading and store the handle to a variable.
    Local $hFileOpen = FileOpen($sFilePath, $FO_READ)
    If $hFileOpen = -1 Then
        MsgBox($MB_SYSTEMMODAL, "", "An error occurred when reading the file.")
        Return False
    EndIf

    ; Read the fist line of the file using the handle returned by FileOpen.
    Local $sFileRead = FileReadLine($hFileOpen, 1)

    ; Close the handle returned by FileOpen.
    FileClose($hFileOpen)

    ; Display the first line of the file.
    ;MsgBox($MB_SYSTEMMODAL, "", "First line of the file:" & @CRLF & $sFileRead)
	ClipPut($sFileRead)

    ; Delete the temporary file.
   ; FileDelete($sFilePath)
EndFunc   ;==>Example

; Create a file.
Func FileCreate($sFilePath, $sString)
   ;Msgbox("", "", "Inside File Create" & @CRLF & "")
   ;MsgBox($MB_SYSTEMMODAL, "Title", "This message box will timeout after 10 seconds or select the OK button.", 10)
    Local $bReturn = True ; Create a variable to store a boolean value.
    If FileExists($sFilePath) = 0 Then $bReturn = FileWrite($sFilePath, $sString) = 1 ; If FileWrite returned 1 this will be True otherwise False.
    Return $bReturn ; Return the boolean value of either True of False, depending on the return value of FileWrite.
EndFunc   ;==>FileCreate#endregion --- Au3Recorder generated code End ---
