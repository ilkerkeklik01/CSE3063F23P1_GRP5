from enum import Enum

class RegistrationStatus(Enum):
    Rejected = "Rejected"
    Confirmed = "Confirmed"
    Active = "Active"


import json
from enum import Enum

class RegistrationStatusEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, Enum):
            return obj.value.decode('utf-8')
        return super().default(obj)
