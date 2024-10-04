'''
    Daniel Victor V. Vasquez
    BSIT - 2A
'''

import random

print("<AM> Let's play RPS!")

while True:
    print("<AM> Here are your options: \n1 = rock, 2 = paper, or 3 = scissors")

    myChoice = input("\n<USER> ")
    amChoice = random.randint(1, 3)

    if myChoice in ["1", "2", "3"]:
        myChoice = int(myChoice)
        print(f"\n<> You chose {myChoice}")
        print(f"<> AM chose {amChoice}")

        if myChoice == amChoice:
            print("\n<AM> Draw!")
        elif (myChoice - amChoice) % 3 in [1, -2]:
            print("\n<AM> You win!")
        else:
            print("\n<AM> AM wins! HA HA HA")
    else:
        print("\n<AM> Invalid choice. You lost in my book.")

    rematch = input("\n<AM> Want a rematch? (Y/N): ").lower()
    if rematch != "y":
        print("<AM> Good game.")
        break
