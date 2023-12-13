package Login;

import Domain.Department;

public interface ILoginStrategy {

    public abstract void login(Department department);
}
