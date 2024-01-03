from abc import ABC, abstractmethod
from Domain.Department import Department

class ILoginStrategy(ABC):

    @abstractmethod
    def login(self, department: Department):
        pass
