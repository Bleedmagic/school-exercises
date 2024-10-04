'''
    Daniel Victor V. Vasquez
    BSIT - 2A
'''

def check(word):
    return word == word[::-1]

print("This is a palindrome checker.")

word = input("\nEnter a word: ")
is_palindrome = check(word)

if is_palindrome:
    print(f"{word} is a palindrome.")
else:
    print(f"{word} is not a palindrome.")