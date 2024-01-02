import random

class IDGenerator:
    def __init__(self):
        self.id = ""
        self.id_prefix = ""
        self.id_suffix = ""
        self.digit_count = 0
        self.used_ids = []
        self._is_id_unique = False  # Changed to avoid recursion

    @property
    def is_id_unique(self):
        return self._is_id_unique

    @is_id_unique.setter
    def is_id_unique(self, is_id_unique):
        self._is_id_unique = is_id_unique

    def set_digit_count(self, digit_count):
        self.digit_count = digit_count

    def set_id_prefix(self, id_prefix):
        self.id_prefix = id_prefix

    def set_id_suffix(self, id_suffix):
        self.id_suffix = id_suffix

    def set_used_ids(self, used_ids):
        self.used_ids = used_ids

    def generate_id(self):
        random.seed()
        sb = []
        sb.append(self.id_prefix)

        while True:
            for i in range(len(self.id_prefix), self.digit_count):
                sb.append(str(random.randint(0, 9)))

            if "".join(sb) not in self.used_ids:
                break

        sb.append(self.id_suffix)
        self.id = "".join(sb)
        return self.id
