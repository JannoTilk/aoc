import datetime

start = datetime.datetime.now()

_input = ""

# Read the file and convert each line into a list of characters
with open("day15.txt", 'r') as f:
# with open("day15_test.txt", 'r') as f:
# with open("day15_test2.txt", 'r') as f:
    _input = f.read()


_sum = 0
_input = _input.split(",")
print(_input)

for s in _input:
    sub_sum = 0
    for c in s:
        _ascii = ord(c)
        sub_sum += _ascii
        sub_sum = sub_sum * 17
        sub_sum = sub_sum % 256
    _sum += sub_sum
print(_sum)

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)
