#!/bin/python

from sklearn.naive_bayes import MultinomialNB
from sklearn.feature_extraction.text import CountVectorizer
from sklearn import metrics
import csv
import random
import math


with open("data.csv", 'r', encoding="ISO-8859-1") as file:
    data = list(csv.reader(file, delimiter=';'))

random.shuffle(data)

splitpoint = math.floor(len(data) * 0.8)
train, test = data[:splitpoint], data[splitpoint:]

# Generate counts from text using a vectorizer.  There are other vectorizers available, and lots of options you can set.
# This performs our step of computing word counts.
vectorizer = CountVectorizer(stop_words='english')
train_features = vectorizer.fit_transform([r[1] for r in train])
test_features = vectorizer.transform([r[1] for r in test])

# Fit a naive bayes model to the training data.
# This will train the model using the word counts we computer, and the existing classifications in the training set.
nb = MultinomialNB()
nb.fit(train_features, [int(r[0]) for r in train])

# Now we can use the model to predict classifications for our test features.
predictions = nb.predict(test_features)
actual = [int(r[0]) for r in test]

# Compute the error.  It is slightly different from our model because the internals of this process work differently from our implementation.
fpr, tpr, thresholds = metrics.roc_curve(actual, predictions, pos_label=1)
f1 = metrics.f1_score(actual, predictions)
print("F1-Score: {0} %".format(f1 * 100))
