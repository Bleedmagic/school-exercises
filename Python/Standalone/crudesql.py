# Author: Daniel Victor V. Vasquez
# Course: BSIT - 2A
# XAMPP > VasquezPT_db > StudentRecords

import mysql.connector

mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    password="",
    database="VasquezPT_DB"
)

cursor = mydb.cursor()

create_table_query = """
CREATE TABLE IF NOT EXISTS StudentRecords (
    StudentID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Course VARCHAR(255) NOT NULL,
    GWA FLOAT
)
"""

cursor.execute(create_table_query)
mydb.commit()

def create():
    while True:
        name = input("Enter student name: ")
        if name.isalpha():
            break
        else:
            print("\nInvalid input. Name should contain only alphabetic characters.")
    
    while True:
        course = input("Enter course: ")
        if course.isalpha():
            break
        else:
            print("\nInvalid input. Course should contain only alphabetic characters.")
    
    while True:
        try:
            gwa = float(input("Enter General Weighted Average (GWA): "))
            break
        except ValueError:
            print("\nInvalid input. GWA should be a valid floating-point number.")
    
    insert_query = "INSERT INTO StudentRecords (Name, Course, GWA) VALUES (%s, %s, %s)"
    cursor.execute(insert_query, (name, course, gwa))
    mydb.commit()
    print("\nStudent record created successfully.")

def read():
    select_query = "SELECT * FROM StudentRecords"
    cursor.execute(select_query)
    student_records = cursor.fetchall()

    for record in student_records:
        print(f"StudentID: {record[0]}, Name: {record[1]}, Course: {record[2]}, GWA: {record[3]}")

def update():
    while True:
        try:
            student_id = int(input("Enter the ID of the student to update: "))
            
            cursor.execute("SELECT * FROM StudentRecords WHERE StudentID = %s", (student_id,))
            existing_record = cursor.fetchone()
            
            if existing_record is None:
                print(f"Student record with StudentID {student_id} does not exist.")
            else:
                break
        except ValueError:
            print("\nInvalid input. Please enter a valid student ID (an integer).")
    
    if existing_record is None:
        print(f"Student record with StudentID {student_id} does not exist.")
        return

    while True:
        name = input("Enter the new name: ")
        if name.isalpha():
            break
        else:
            print("\nInvalid input. Name should contain only alphabetic characters.")
    
    while True:
        course = input("Enter the new course: ")
        if course.isalpha():
            break
        else:
            print("\nInvalid input. Course should contain only alphabetic characters.")
    
    while True:
        try:
            gwa = float(input("Enter the new GWA: "))
            break
        except ValueError:
            print("\nInvalid input. GWA should be a valid floating-point number.")
    
    update_query = "UPDATE StudentRecords SET Name = %s, Course = %s, GWA = %s WHERE StudentID = %s"
    cursor.execute(update_query, (name, course, gwa, student_id))
    mydb.commit()
    print("\nStudent record updated successfully.")

def delete():
    student_id = int(input("Enter the StudentID of the record to delete: "))
    delete_query = "DELETE FROM StudentRecords WHERE StudentID = %s"
    cursor.execute(delete_query, (student_id,))
    mydb.commit()
    print("\nStudent record deleted successfully.")

while True:
    print("\n==================================")
    print("|       CRUDE OPERATIONS MENU    |")
    print("==================================")
    print("| Options:                       |")
    print("|        [1] Create Data         |")
    print("|        [2] Read Records        |")
    print("|        [3] Update              |")
    print("|        [4] Delete              |")
    print("|        [5] Exit                |")
    print("|                                |")
    print("| Pick a choice: [_]             |")
    print("==================================")
    
    choice = input("\n<USER> ")
    
    if choice == "1":
        create()
    elif choice == "2":
        read()
    elif choice == "3":
        update()
    elif choice == "4":
        delete()
    elif choice == "5":
        break
    else:
        print("\nInvalid choice. Please choose a valid option.")

mydb.close()