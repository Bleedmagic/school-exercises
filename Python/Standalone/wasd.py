import keyboard


def wasd_to_arrows(e):
    if e.name == "w":
        keyboard.press_and_release("up")
    elif e.name == "a":
        keyboard.press_and_release("left")
    elif e.name == "s":
        keyboard.press_and_release("down")
    elif e.name == "d":
        keyboard.press_and_release("right")


keyboard.on_press(wasd_to_arrows)
keyboard.wait("q")
keyboard.unhook_all()
