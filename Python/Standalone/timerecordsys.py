import time
import datetime

# Initialize empty dictionaries and lists to store employee data and timekeeping records
employee_data = {}
timekeeping_records = []

# Function to display the welcome screen and menu
def display_menu():
    print("\nWelcome to the Simple Daily Time Record System")
    print("1. Timekeeping")
    print("2. Register Employee")
    print("3. View Employee")
    print("4. View Timekeeping Records")
    print("5. Exit")

# Function to validate the time format
def is_valid_time_format(time_str):
    try:
        # Attempt to parse the time with the format
        time.strptime(time_str, '%I:%M %p')
        return True
    except ValueError:
        return False

# Function to validate the date format
def is_valid_date_format(date_str):
    try:
        # Attempt to parse the date with the format
        datetime.datetime.strptime(date_str, '%Y-%m-%d')
        return True
    except ValueError:
        return False

# Function to handle timekeeping with input validation
def timekeeping():
    employee_id = input("Enter Employee ID: ")
    
    # Validate employee ID exists
    if employee_id not in employee_data:
        print("Employee not found. Please register the employee first.")
        return
    
    while True:
        time_in = input("Enter Time In (hh:mm AM/PM): ")
        if is_valid_time_format(time_in):
            break
        else:
            print("Invalid time format. Please use hh:mm AM/PM for time.")
    
    while True:
        time_out = input("Enter Time Out (hh:mm AM/PM): ")
        if is_valid_time_format(time_out):
            break
        else:
            print("Invalid time format. Please use hh:mm AM/PM for time.")
    
    while True:
        date = input("Enter Date (YYYY-MM-DD): ")
        if is_valid_date_format(date):
            break
        else:
            print("Invalid date format. Please use YYYY-MM-DD for the date.")
    
    record = {"Employee ID": employee_id, "Time In": time_in, "Time Out": time_out, "Date": date}
    timekeeping_records.append(record)
    print("Timekeeping data recorded successfully!")

# Function to register an employee
def register_employee():
    employee_id = input("Enter Employee ID: ")
    employee_name = input("Enter Employee Name: ")
    employee_position = input("Enter Employee Position: ")
    
    employee_info = {"Name": employee_name, "Position": employee_position}
    employee_data[employee_id] = employee_info
    print("Employee registered successfully!")

# Function to view employee details
def view_employee():
    employee_id = input("Enter Employee ID: ")
    
    if employee_id in employee_data:
        employee_info = employee_data[employee_id]
        print(f"Employee ID: {employee_id}")
        print(f"Employee Name: {employee_info['Name']}")
        print(f"Employee Position: {employee_info['Position']}")
    else:
        print("Employee not found!")

# Function to view timekeeping records
def view_timekeeping_records():
    if not timekeeping_records:
        print("Timekeeping records are empty.")
    else:
        for record in timekeeping_records:
            print("Employee ID:", record["Employee ID"])
            print("Time In:", record["Time In"])
            print("Time Out:", record["Time Out"])
            print("Date:", record["Date"])
            print("-" * 20)

# Main program loop
while True:
    display_menu()
    
    choice = input("Enter your choice (1/2/3/4/5): ")
    
    if choice == '1':
        timekeeping()
    elif choice == '2':
        register_employee()
    elif choice == '3':
        view_employee()
    elif choice == '4':
        view_timekeeping_records()
    elif choice == '5':
        print("Exiting the program. Goodbye!")
        break
    else:
        print("Invalid choice. Please enter a valid option (1/2/3/4/5).")
