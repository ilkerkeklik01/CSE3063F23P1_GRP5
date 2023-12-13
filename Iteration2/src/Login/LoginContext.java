package Login;

import Domain.Department;

public class LoginContext {

    public ILoginStrategy loginStrategy;


    public void setStrategy(ILoginStrategy strategy){
        this.loginStrategy = strategy;
    }

    public void login(Department department){
        this.loginStrategy.login(department);
    }
}
