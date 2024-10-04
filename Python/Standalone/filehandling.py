# Author: Daniel Victor V. Vasquez
# Course: BSIT - 2A

while True:
    print("\n=================================")
    print("|       FILE HANDLING MENU       |")
    print("==================================")
    print("| Options:                       |")
    print("|        [1] Read File           |")
    print("|        [2] Write File          |")
    print("|        [3] Append File         |")
    print("|        [4] Open File           |")
    print("|        [5] Exit                |")
    print("|                                |")
    print("| Pick a choice: [_]             |")
    print("==================================")

    user_choice = input("\n<USER> ")

    if user_choice == '1':
        file_name = input("\nEnter File to Read: ")
        try:
            with open(file_name, "r") as file:
                if file.readable():
                    print("\n:::::CONTENTS OF THE FILE SELECTED:::::")
                    print(file.read())
                else:
                    print("\nThe file is not readable.")
        except FileNotFoundError:
            print("\nFile not found!")

    elif user_choice == '2':
        file_name = input("\nEnter File to Write: ")
        try:
            with open(file_name, "w") as file:
                if file.writable():
                    print("\n1. Write a single line")
                    print("2. Write multiple lines")
                    write_option = input("\nEnter your choice (1 or 2): ")
                    if write_option == '1':
                        text = input("\nEnter text to write: ")
                        file.write(text)
                        print("\nWritten Successfully!")
                    elif write_option == '2':
                        try:
                            num_lines = int(input("\nHow many lines to write: "))
                            lines = [input("Enter line {}: ".format(i + 1)) + "\n" for i in range(num_lines)]
                            file.writelines(lines)
                            print("\nWritten Successfully!")
                        except ValueError:
                            print("\nInvalid number of lines. Please enter an integer.")
                    else:
                        print("\nInvalid Option!")
                else:
                    print("\nThe file is not writable.")
        except FileNotFoundError:
            print("\nFile not found!")

    elif user_choice == '3':
        file_name = input("\nEnter File to Append: ")
        try:
            with open(file_name, "a") as file:
                if file.writable():
                    print("\n1. Append a single line")
                    print("2. Append multiple lines")
                    append_option = input("\nEnter your choice (1 or 2): ")
                    if append_option == '1':
                        text = input("\nEnter text to append: ")
                        file.write("\n" + text)
                        print("\nAppended Successfully!")
                    elif append_option == '2':
                        try:
                            num_lines = int(input("\nHow many lines to append: "))
                            lines = [input("Enter line {}: ".format(i + 1)) + "\n" for i in range(num_lines)]
                            file.writelines(lines)
                            print("\nAppended Successfully!")
                        except ValueError:
                            print("\nInvalid number of lines. Please enter an integer.")
                    else:
                        print("\nInvalid Option!")
                else:
                    print("\nThe file is not writable.")
        except FileNotFoundError:
            print("\nFile not found!")

    elif user_choice == '4':
        file_name = input("\nEnter File to Open: ")
        try:
            with open(file_name, "r") as file:
                if file.readable():
                    print("\n:::::CONTENTS OF THE FILE SELECTED:::::")
                    print(file.read())

                    action = input("\nDo you want to [R]ead, [W]rite, or [A]ppend (R/W/A)? ").strip().upper()

                    if action == 'R':
                        pass
                    elif action == 'W':
                        with open(file_name, "w") as file_to_write:
                            if file_to_write.writable():
                                text = input("\nEnter text to write: ")
                                file_to_write.write(text)
                                print("\nWritten Successfully!")
                            else:
                                print("\nThe file is not writable.")
                    elif action == 'A':
                        with open(file_name, "a") as file_to_append:
                            if file_to_append.writable():
                                text = input("\nEnter text to append: ")
                                file_to_append.write("\n" + text)
                                print("\nAppended Successfully!")
                            else:
                                print("\nThe file is not writable.")
                    else:
                        print("\nInvalid Choice!")
                else:
                    print("\nThe file is not readable.")
        except FileNotFoundError:
            print("\nFile not found!")

    elif user_choice == '5':
        print("\nExiting the program. \nGoodbye!")
        break
    else:
        print("\nInvalid Choice!")
