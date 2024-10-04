'''
    Daniel Victor V. Vasquez
    BSIT - 2A
'''

def cube(userInput):
    if userInput >= 0:
        return userInput ** (1/3)
    elif userInput < 0:
        return -(abs(userInput) ** (1/3))

print("This determines an inputted number's cube root.")

userInput = float(input("\nEnter a number: "))
cubeRoot = cube(userInput)

print(f"The cube root of {userInput} is: {cubeRoot:.2f}")
