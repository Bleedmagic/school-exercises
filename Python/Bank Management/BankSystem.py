# Author: Daniel Victor V. Vasquez
# Course: BSIT - 2A

import mysql.connector
from decimal import Decimal, DecimalException
from BankClient import BankClient
from BankAccount import BankAccount

class BankSystem:
    def __init__(self, host, user, password, database):
        self.accounts = []
        self.clients = []
        self.db = mysql.connector.connect(
            host=host,
            user=user,
            password=password,
            database=database
        )
        self.cursor = self.db.cursor()

    def create_account(self):
        print("\n:::New Account:::")
        try:
            client_id_input = input("\nEnter existing client ID: ")

            if not client_id_input.isdigit() or int(client_id_input) <= 0:
                raise ValueError("Invalid client ID. Please enter a valid positive integer.")

            client_id = int(client_id_input)

            self.cursor.execute("SELECT * FROM clients WHERE client_id = %s", (client_id,))
            existing_client = self.cursor.fetchone()

            if existing_client:
                balance = input("Enter initial account balance: ")

                valid_balance, validated_balance = self.is_valid_balance(balance)
                if not valid_balance:
                    return False

                new_account = BankAccount(validated_balance, client_id)

                self.cursor.execute("INSERT INTO accounts (balance, client_id) VALUES (%s, %s)",
                                    (validated_balance, client_id))
                self.db.commit()

                self.accounts.append(new_account)

                print("\nAccount created successfully.")
                return True
            else:
                print(f"Invalid client ID. Client with ID '{client_id}' does not exist.")
                return False
        except ValueError as ve:
            print(f"Error: {ve}")
            return False

    def create_client(self):
        print("\n:::New Client:::")
        try:
            client_id = input("\nEnter client ID: ")
            if not client_id.isdigit() or int(client_id) <= 0:
                raise ValueError("Invalid client ID. Please enter a valid positive integer.")

            self.cursor.execute("SELECT * FROM clients WHERE client_id = %s", (client_id,))
            existing_client = self.cursor.fetchone()

            if existing_client:
                print(f"Client with ID {client_id} already exists. Please choose a different client ID.")
                return False

            name = input("Enter client name: ")
            if not name.replace(" ", "").isalpha():
                raise ValueError("Invalid name. Please enter a valid name.")

            age = input("Enter client age: ")

            if not age.isdigit() or int(age) <= 0:
                raise ValueError("Invalid age. Please enter a positive integer for the client's age.")

            new_client = BankClient(client_id, name, int(age), None)

            self.clients.append(new_client)

            self.cursor.execute("INSERT INTO clients (client_id, name, age) VALUES (%s, %s, %s)",
                                (client_id, name, age))
            self.db.commit()

            print("\nClient created successfully.")
            return True
        except ValueError as ve:
            print(f"Error: {ve}")
            return False

    def find_account_menu(self):
        print("\n:::Find an Account:::")
        account_number = input("\nEnter account ID: ")
        client = self.find_account(account_number)

        if client:
            client.print_details()
        else:
            print("Account not found or invalid input.")

    def find_account(self, account_number):
        try:
            self.cursor.execute("SELECT accounts.account_number, accounts.balance, accounts.client_id FROM accounts WHERE accounts.account_number = %s", (account_number,))
            account_data = self.cursor.fetchone()

            if account_data:
                account_number, balance, client_id = account_data
                return BankAccount(balance, client_id)
            else:
                return None
        except Exception as e:
            print(f"Error finding account: {e}")
            return None

    def find_client_menu(self):
        print("\n:::Find a Client:::")
        client_id = input("\nEnter client ID: ")
        client = self.find_client(client_id)

        if client:
            client.print_details()
        else:
            print("Client not found or invalid input.")

    def find_client(self, client_id):
        try:
            self.cursor.execute("SELECT * FROM clients WHERE client_id = %s", (client_id,))
            client_data = self.cursor.fetchone()

            if client_data:
                client_id, name, age = client_data

                existing_client = next((client for client in self.clients if client.get_id_number() == client_id), None)

                if existing_client:
                    return existing_client
                else:
                    new_client = BankClient(client_id, name, age, None)
                    self.clients.append(new_client)
                    return new_client
            else:
                return None
        except Exception as e:
            print(f"Error finding client: {e}")
            return None

    def has_associated_accounts(self, client_id):
        self.cursor.execute("SELECT COUNT(*) FROM accounts WHERE client_id = %s", (client_id,))
        count = self.cursor.fetchone()[0]
        return count > 0

    def is_valid_client_id(self, client_id):
        return client_id.isdigit() and self.find_client(client_id) is not None

    def is_valid_account_number(self, account_number):
        try:
            int(account_number)
            return self.find_account(account_number) is not None
        except ValueError:
            return False

    def is_valid_balance(self, balance):
        try:
            validated_balance = self.validate_positive_decimal(balance, "balance")
            return True, validated_balance
        except ValueError as ve:
            print(f"Error: {ve}")
            return False, None

    def validate_positive_decimal(self, value, field_name):
        try:
            decimal_value = Decimal(value)
            if decimal_value < 0:
                raise ValueError(f"{field_name} must be a positive value.")
            return decimal_value
        except DecimalException:
            raise ValueError(f"Invalid {field_name}. Please enter a valid positive decimal value.")

    def is_valid_amount(self, amount, field_name):
        try:
            validated_amount = self.validate_positive_decimal(amount, field_name)
            return True, validated_amount
        except ValueError as ve:
            print(f"Error: {ve}")
            return False, None

    def has_clients(self):
        try:
            self.cursor.execute("SELECT COUNT(*) FROM clients")
            count = self.cursor.fetchone()[0]
            return count > 0
        except Exception as e:
            print(f"Error checking clients: {e}")
            return False

    def display_main_menu(self):
        while True:
            print("\n=====================================")
            print("|       BANK SYSTEM MAIN MENU       |")
            print("=====================================")
            print("|  Options:                         |")
            print("|         [1] Account Management    |")
            print("|         [2] Client Management     |")
            print("|         [3] Quit                  |")
            print("|                                   |")
            print("|  Pick a choice: [_]               |")
            print("=====================================")

            choice = input("\n<USER> ")

            if choice == '1':
                if not self.has_clients():
                    print("No clients found. Please create a client first.")
                else:
                    self.account_management()
            elif choice == '2':
                self.client_management()
            elif choice == '3':
                print("\nClosing database connection...")
                print("\nProgram has been terminated. \nGoodbye!")
                break
            else:
                print("Invalid choice. Please enter a valid option.")

    def account_management(self):
        while True:
            print("\n==========================================")
            print("|        ACCOUNT MANAGEMENT MENU~        |")
            print("==========================================")
            print("|  Options:                              |")
            print("|        [1] New Account                 |")
            print("|        [2] List All Accounts           |")
            print("|        [3] Find an Account             |")
            print("|        [4] Deposit to an Account       |")
            print("|        [5] Withdraw from an Account    |")
            print("|        [6] Close an Account            |")
            print("|        [7] Return to Main Menu         |")
            print("|                                        |")
            print("|  Pick a choice: [_]                    |")
            print("==========================================")

            choice = input("\n<USER> ")

            if choice == '1':
                self.create_account()
            elif choice == '2':
                self.list_all_accounts()
            elif choice == '3':
                self.find_account_menu()
            elif choice == '4':
                self.deposit_to_account()
            elif choice == '5':
                self.withdraw_from_account()
            elif choice == '6':
                self.close_account()
            elif choice == '7':
                break
            else:
                print("Invalid choice. Please enter a valid option.")

    def client_management(self):
        while True:
            print("\n======================================")
            print("|       CLIENT MANAGEMENT MENU       |")
            print("======================================")
            print("|  Options:                          |")
            print("|         [1] New Client             |")
            print("|         [2] List All Clients       |")
            print("|         [3] Find Client            |")
            print("|         [4] Delete a Client        |")
            print("|         [5] Return to Main Menu    |")
            print("|                                    |")
            print("|  Pick a choice: [_]                |")
            print("======================================")

            choice = input("\n<USER> ")

            if choice == '1':
                self.create_client()
            elif choice == '2':
                self.list_all_clients()
            elif choice == '3':
                self.find_client_menu()
            elif choice == '4':
                self.delete_client()
            elif choice == '5':
                break
            else:
                print("Invalid choice. Please enter a valid option.")

    def list_all_clients(self):
        try:
            self.cursor.execute("SELECT client_id, name FROM clients")
            clients_data = self.cursor.fetchall()

            if clients_data:
                print("\n:::List of All Clients:::")
                for client_data in clients_data:
                    client_id, name = client_data
                    print(f"\nClient ID: {client_id}, Name: {name}")
            else:
                print("No clients found.")
        except Exception as e:
            print(f"Error listing all clients: {e}")

    def list_all_accounts(self):
        try:
            self.cursor.execute("SELECT * FROM accounts")
            accounts_data = self.cursor.fetchall()

            if accounts_data:
                print("\n:::List of All Accounts:::")
                for account_data in accounts_data:
                    account_number, balance, client_id = account_data
                    print(f"\nAccount ID: {account_number}, Balance: {balance}, Client ID: {client_id}")
            else:
                print("No accounts found.")
        except Exception as e:
            print(f"Error listing all accounts: {e}")

    def deposit_to_account(self):
        print("\n:::Deposit to an Account:::")
        try:
            account_number = input("\nEnter account ID: ")

            try:
                account_number = Decimal(account_number)
            except DecimalException:
                raise ValueError("Invalid account ID. Please enter a valid numerical value.")

            if not self.is_valid_account_number(account_number):
                raise ValueError(f"Account with ID {account_number} does not exist. Please check your inputs.")

            amount = input("Enter deposit amount: ")

            try:
                validated_amount = Decimal(amount)
            except DecimalException:
                raise ValueError("Invalid deposit amount. Please enter a valid positive decimal value.")

            account = self.find_account(account_number)

            if account:
                success = account.deposit(validated_amount)

                if success:
                    self.cursor.execute("UPDATE accounts SET balance = %s WHERE account_number = %s",
                                        (account.get_balance(), account_number))
                    self.db.commit()

                    print("\n=== Deposit Successful ===")
                    account.print_details()
                else:
                    print("Invalid input. Make sure you entered a valid amount.")
            else:
                print("Failed to deposit. Please check your inputs.")
        except ValueError as ve:
            print(f"Error: {ve}")

    def withdraw_from_account(self):
        print("\n:::Withdraw from an Account:::")
        try:
            account_number = input("\nEnter account ID: ")
            try:
                account_number = Decimal(account_number)
            except DecimalException:
                raise ValueError("Invalid account ID. Please enter a valid numerical value.")
            if not self.is_valid_account_number(account_number):
                raise ValueError(f"Account with ID {account_number} does not exist. Please check your inputs.")
            amount = input("Enter withdrawal amount: ")
            try:
                validated_amount = Decimal(amount)
            except DecimalException:
                raise ValueError("Invalid withdrawal amount. Please enter a valid positive decimal value.")
            account = self.find_account(account_number)
            if account:
                success = account.withdraw(validated_amount)
                if success:
                    self.cursor.execute("UPDATE accounts SET balance = %s WHERE account_number = %s",
                                        (account.get_balance(), account_number))
                    self.db.commit()
                    print("\n=== Withdrawal Successful ===")
                    account.print_details()
                else:
                    print("Insufficient funds for withdrawal.")
            else:
                print("Failed to withdraw. Please check your inputs.")
        except ValueError as ve:
            print(f"Error: {ve}")

    def close_account(self):
        print("\n:::Close an Account:::")
        try:
            account_number = input("\nEnter account ID to close: ")
            if not account_number.isdigit():
                raise ValueError("Invalid account ID. Please enter a valid numerical value.")
            if not self.is_valid_account_number(account_number):
                raise ValueError(f"Account with ID {account_number} does not exist. Please check your inputs.")
            confirmation = input(f"Are you sure you want to close account {account_number}? (Y/N): ").lower()
            if confirmation == "y":
                self.cursor.execute("DELETE FROM accounts WHERE account_number = %s", (account_number,))
                self.db.commit()
                self.accounts = [account for account in self.accounts if account.get_client_id() != account_number]
                print(f"\nAccount '{account_number}' closed successfully.")
            else:
                print(f"\nAccount closure for account {account_number} canceled.")
        except ValueError as ve:
            print(f"Error: {ve}")

    def delete_client(self):
        print("\n:::Delete a Client:::")
        try:
            client_id = input("\nEnter client ID to delete: ")
            if not client_id.isdigit():
                raise ValueError("Invalid client ID. Please enter a valid numerical value.")
            if not self.is_valid_client_id(client_id):
                raise ValueError(f"Client with ID {client_id} does not exist. Please check your inputs.")
            if self.has_associated_accounts(client_id):
                raise ValueError("Cannot delete client with associated accounts. Please close the accounts first.")
            confirmation = input(f"Are you sure you want to delete client {client_id}? (Y/N): ").lower()
            if confirmation == 'y':
                self.cursor.execute("DELETE FROM clients WHERE client_id = %s", (client_id,))
                self.db.commit()
                self.clients = [client for client in self.clients if client.get_id_number() != client_id]
                print(f"\nClient '{client_id}' deleted successfully.")
            else:
                print(f"\nDeletion for client {client_id} canceled.")
        except ValueError as ve:
            print(f"Error: {ve}")

    def close_connection(self):
        try:
            if self.db.is_connected():
                self.db.close()
        except Exception as e:
            print(f"Error closing connection: {e}")

if __name__ == "__main__":
    bank_system = BankSystem(host='localhost', user='root', password='', database='bankvasquez_db')
    try:
        bank_system.display_main_menu()
    finally:
        bank_system.close_connection()
