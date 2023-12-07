from collections import defaultdict
from collections import Counter
import datetime

start = datetime.datetime.now()

_input = []

with open("day7.txt", 'r') as f:
# with open("day7_test.txt", 'r') as f:
    for line in f.readlines():
        stripped_line = line.strip()
        if stripped_line:
            _input.append(stripped_line)

_dict = defaultdict(list)

for hand in _input:
    cards = hand.split(" ")[0]
    counter = Counter(cards)
    hand_pair = [hand.split(" ")[0], hand.split(" ")[1]]
    if len(set(cards)) == 1:
        _dict["5"].append(hand_pair)
    elif len(set(cards)) == 2:
        if counter.most_common(1)[0][1] == 4:
            _dict["4"].append(hand_pair)
        else:
            _dict["FH"].append(hand_pair)
    elif len(set(cards)) == 3:
        if counter.most_common(1)[0][1] == 3:
            _dict["3"].append(hand_pair)
        else:
            _dict["2_pairs"].append(hand_pair)
    elif len(set(cards)) == 4:
        _dict["1_pair"].append(hand_pair)
    else:
        _dict["all_different"].append(hand_pair)

ordering = ["A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2"]
hand_ordering = ["all_different", "1_pair", "2_pairs", "3", "FH", "4", "5"]
order_map = {char: index for index, char in enumerate(ordering)}

def custom_sort_key(item):
    return [order_map.get(char, -1) for char in item[0]]

data = _dict

sorted_data = {key: sorted(value, key=custom_sort_key, reverse=True) for key, value in data.items()}

print(sorted_data["all_different"])
i = 1
_sum = 0
for hand_type in hand_ordering:
    # print(hand_type)
    for hand in sorted_data[hand_type]:
        print(hand)
        print(int(hand[1]))
        winning = int(hand[1]) * i
        _sum += winning
        i += 1

print(_sum)
print(i)

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)