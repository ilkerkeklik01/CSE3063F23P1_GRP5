from Login.ILoginStrategy import ILoginStrategy
from Domain.Department import Department

class LoginContext:

    def __init__(self):
        self.login_strategy = None

    def set_strategy(self, strategy: ILoginStrategy):
        self.login_strategy = strategy

    def login(self, department: Department):
        if self.login_strategy:
            self.login_strategy.login(department)
