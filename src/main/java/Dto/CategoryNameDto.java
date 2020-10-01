package Dto;

public class CategoryNameDto {
    private static String category;

    public static String getCategory() {
        return category;
    }

    public static void setCategory(String category) {
        CategoryNameDto.category = category;
    }
}
