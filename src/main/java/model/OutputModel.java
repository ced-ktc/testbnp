package model;

import java.util.List;

public class OutputModel {
    private List<CustomerSummaries> customerSummaries;

    public OutputModel() {
    }

    public OutputModel(List<CustomerSummaries> customerSummaries) {
        this.customerSummaries = customerSummaries;
    }

    public List<CustomerSummaries> getCustomerSummaries() {
        return customerSummaries;
    }

    public void setCustomerSummaries(List<CustomerSummaries> customerSummaries) {
        this.customerSummaries = customerSummaries;
    }

}
