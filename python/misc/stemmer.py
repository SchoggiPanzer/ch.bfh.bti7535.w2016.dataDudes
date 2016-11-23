from nltk.stem.porter import *
from nltk import word_tokenize


stemmer = PorterStemmer()

with open('../resources/hamlet.txt', 'r') as f:
    words = f.read(100)
    tokens = word_tokenize(words)

    singular = [stemmer.stem(word) for word in tokens]
    print(singular)
