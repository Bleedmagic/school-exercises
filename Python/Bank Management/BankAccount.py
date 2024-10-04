# Author: Daniel Victor V. Vasquez
# Course: BSIT - 2A

class BankAccount:
    def __init__(self, initial_deposit, client_id):
        self.balance = initial_deposit
        self.client_id = client_id

    def get_balance(self):
        return self.balance

    def get_client_id(self):
        return self.client_id

    def print_details(self):
        print(f"\nClient ID: {self.client_id}")
        print(f"Balance: {self.balance}")

    def deposit(self, amount):
        if amount > 0:
            self.balance += amount
            return True
        else:
            return False

    def withdraw(self, amount):
        if 0 < amount <= self.balance:
            self.balance -= amount
            return True
        else:
            return False
