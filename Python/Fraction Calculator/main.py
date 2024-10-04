from fractions import Fraction

def get_fraction_input(prompt):
    while True:
        try:
            numerator = int(input(prompt + " numerator: "))
            denominator = int(input(prompt + " denominator: "))
            return Fraction(numerator, denominator)
        except ValueError:
            print("Invalid input. Please enter valid integers.")

def main():
    print("\nFraction Calculator")
    fraction1 = get_fraction_input("Enter fraction 1")
    fraction2 = get_fraction_input("Enter fraction 2")

    print(f"\nFraction 1: {fraction1}")
    print(f"Fraction 2: {fraction2}")

    print(f"\nAddition: {fraction1} + {fraction2} = {fraction1 + fraction2}")
    print(f"Multiplication: {fraction1} * {fraction2} = {fraction1 * fraction2}")
    print(f"Subtraction: {fraction1} - {fraction2} = {fraction1 - fraction2}")
    print(f"Division: {fraction1} / {fraction2} = {fraction1 / fraction2}")

    print(f"\nEqual: {fraction1} == {fraction2} is {fraction1 == fraction2}")
    print(f"Less than: {fraction1} < {fraction2} is {fraction1 < fraction2}")
    print(f"Greater than: {fraction1} > {fraction2} is {fraction1 > fraction2}")

if __name__ == "__main__":
    main()