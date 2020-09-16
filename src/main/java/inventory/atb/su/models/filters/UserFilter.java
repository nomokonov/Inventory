package inventory.atb.su.models.filters;

import java.util.Optional;

public class UserFilter {
    private Optional<String> codeDepartment;
    private Optional<String> mol;

    public Optional<String> getCodeDepartment() {
        return codeDepartment;
    }

    public void setCodeDepartment(Optional<String> codeDepartment) {
        this.codeDepartment = codeDepartment;
    }

    public Optional<String> getMol() {
        return mol;
    }

    public void setMol(Optional<String> mol) {
        this.mol = mol;
    }
}
