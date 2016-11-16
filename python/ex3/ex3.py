import nltk


def read_entities():
    with open('sample.txt', 'r') as afile:
        data = afile.read().replace('\n', '')
        tokens = nltk.word_tokenize(data)
        tagged = nltk.pos_tag(tokens)
        entities = nltk.chunk.ne_chunk(tagged)
        return entities


def filter_NER(entities):
    named_entities = []
    for entity in entities.subtrees():
        if entity.label() == 'PERSON':
            named_entities.append(entity)
    return named_entities


ne = read_entities()
ner = filter_NER(ne)
print(ner)
