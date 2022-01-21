package org.example.practice.office.dto;

public class TestDTO {
    private Long id;

    public TestDTO(Long id) {
        this.id = id;
    }

    public TestDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
                "id=" + id +
                '}';
    }
}
