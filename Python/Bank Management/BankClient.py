# Author: Daniel Victor V. Vasquez
# Course: BSIT - 2A

class BankClient:
    def __init__(self, client_id, name, age, account):
        self.client_id = client_id
        self.name = name
        self.age = age
        self.account = account

    def get_name(self):
        return self.name

    def get_id_number(self):
        return self.client_id

    def get_account(self):
        return self.account

    def print_details(self):
        print(f"\nClient ID: {self.client_id}")
        print(f"Name: {self.name}")
        print(f"Age: {self.age}")
