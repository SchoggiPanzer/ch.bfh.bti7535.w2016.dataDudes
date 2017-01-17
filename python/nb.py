from sklearn.naive_bayes import MultinomialNB
from sklearn.feature_extraction.text import CountVectorizer
# from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.pipeline import FeatureUnion
from sklearn.model_selection import train_test_split
from sklearn import metrics
# from nltk.corpus import stopwords
import pandas as pd
import nltk
from nltk import word_tokenize
from nltk.stem import WordNetLemmatizer
import os.path

# Downloads the nltk data
if os.path.exists("/usr/local/share/nltk_data") or os.path.exists("~/nltk_data"):
    print("Found nltk_data")
else:
    nltk.download('all')

# Import the review data with pandas
data = pd.read_csv("data.csv", sep=';', names=['sentiment', 'text'], encoding='ISO-8859-1')
# print(data.head())

# Stop words feature with TF-IDF
# stopset = set(stopwords.words('english'))
# tfidf_feature = TfidfVectorizer(use_idf=True, lowercase=True, stop_words=stopset)

# Simple stop words feature
count_feature = CountVectorizer(stop_words='english')


# Lemma tokenizer
class LemmaTokenizer(object):

    def __init__(self):
        self.wnl = WordNetLemmatizer()

    def __call__(self, doc):
        return [self.wnl.lemmatize(t) for t in word_tokenize(doc)]


lemma_feature = CountVectorizer(tokenizer=LemmaTokenizer())

# Chain the features
feature_pipeline = FeatureUnion([
    # ('count', count_feature),
    # ('tfidf', tfidf_feature),
    ('lemma', lemma_feature),
], n_jobs=1)

# Fit the data (x dependent variable, y independent variable) one after the other in the pipeline
x = feature_pipeline.fit_transform(data.text)
y = data.sentiment

# Split the data into test and training sets
x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.2)

# Train (fit) the model with multinomial naive bayes. This type of NB is used for discrete features
# like in our case word countings.
nb = MultinomialNB()
nb.fit(x_train, y_train)

# Calculate the results
actual = y_test
predictions = nb.predict(x_test)

precision, recall, thresholds = metrics.precision_recall_curve(actual, predictions)
accuracy = metrics.accuracy_score(actual, predictions)
print("Accuracy: {0} %".format(accuracy * 100))
