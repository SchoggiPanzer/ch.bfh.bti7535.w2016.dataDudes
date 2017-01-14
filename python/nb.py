#!/bin/python

from sklearn.naive_bayes import MultinomialNB, GaussianNB
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.pipeline import FeatureUnion
from sklearn import metrics
from nltk.stem import WordNetLemmatizer
from nltk import word_tokenize
import csv
import random
import math


with open("data.csv", 'r', encoding="ISO-8859-1") as file:
    data = list(csv.reader(file, delimiter=';'))

random.shuffle(data)

splitpoint = math.floor(len(data) * 0.8)
train, test = data[:splitpoint], data[splitpoint:]


class LemmaTokenizer(object):

    def __init__(self):
        self.wnl = WordNetLemmatizer()

    def __call__(self, doc):
        return [self.wnl.lemmatize(t) for t in word_tokenize(doc)]


# Generate counts from text using a vectorizer.  There are other vectorizers available, and lots of options you can set.
# This performs our step of computing word counts.
count_feature = CountVectorizer(stop_words='english')
lemma_feature = CountVectorizer(tokenizer=LemmaTokenizer())
tfidf_feature = TfidfVectorizer(min_df=1)

# Chain the features
feature_pipeline = FeatureUnion([
	('counter', count_feature),
	('tfidf', tfidf_feature),
        ('lemma', lemma_feature)],
	n_jobs=1)
train_features = feature_pipeline.fit_transform([r[1] for r in train])
test_features = feature_pipeline.transform([r[1] for r in test])

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
