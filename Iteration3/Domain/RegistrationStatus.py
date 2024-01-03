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
            return obj.value
        return super().default(obj)
