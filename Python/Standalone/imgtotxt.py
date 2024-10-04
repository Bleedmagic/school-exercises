from PIL import Image
import pytesseract
import re
import os

pytesseract.pytesseract.tesseract_cmd = (
    "C:\\Program Files\\Tesseract-OCR\\tesseract.exe"
)

def clean_path(file_path):
    return re.sub(r"[^\x00-\x7F]+", "", file_path).strip()

output_directory = "D:\\Workspace\\Programming\\_Projects\\Python\\Image to Text\\scanned_images"

dire = input("File Path: ")

cleaned_dire = clean_path(dire)

try:
    im = Image.open(cleaned_dire)
except Exception as e:
    print(f"Error opening the image file: {e}")
    exit()

language = input("Language (3 letters): ")

try:
    text = pytesseract.image_to_string(im, lang=language)
except Exception as e:
    print(f"Error during text extraction: {e}")
    exit()

filename = input("Output Name: ")

output_path = os.path.join(output_directory, f"{filename}-{language}.txt")

with open(output_path, "w") as file1:
    file1.write(text)

print("DONE!")
