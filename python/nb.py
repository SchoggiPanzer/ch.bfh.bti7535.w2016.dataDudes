from sklearn.naive_bayes import MultinomialNB
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.pipeline import FeatureUnion
from sklearn.model_selection import train_test_split
from sklearn import metrics
from nltk.corpus import stopwords
import pandas as pd
import nltk
from stemmer import tokenize_and_stem


# Downloads the nltk data
nltk.download('all')

# Import the review data with pandas
data = pd.read_csv("data.csv", sep=';', names=['sentiment', 'text'], encoding='ISO-8859-1')

# Simple stop words feature
count_feature = CountVectorizer(stop_words='english')

# Stemmer feature
stemmer_feature = CountVectorizer(tokenizer=tokenize_and_stem)

# Stop words feature with TF-IDF
stopset = set(stopwords.words('english'))
tfidf_feature = TfidfVectorizer(use_idf=True, lowercase=True, stop_words=stopset)

# Chain the features
feature_pipeline = FeatureUnion([
    ('count', count_feature),
    ('stemmer', stemmer_feature),
    ('tfidf', tfidf_feature),
], n_jobs=1)

# Fit the data (x dependent variable, y independent variable)
x = feature_pipeline.fit_transform(data.text)
y = data.sentiment

# Split the data into test and training sets
x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2, random_state=42)

# Train the model
nb = MultinomialNB()
nb.fit(x_train, y_train)

# Calculate the results
actual = y_test
predictions = nb.predict(x_test)
# score = metrics.roc_auc_score(actual, predictions)
# print("Score: {0} %".format(score * 100))
accuracy = metrics.accuracy_score(actual, predictions)
print("Accuracy: {0} %".format(accuracy * 100))
