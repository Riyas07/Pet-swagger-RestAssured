package pet.Pojo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AddPetsPojo {
  private   int id;
  private   String name;
  private   String status;
  private   Map<String, Object>category;
   private List<String>photoUrls;
  private   List<Map<String,Object>>tags;

  public static AddPetsPojo getInstance()
  {
      return new AddPetsPojo();
  }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> getCategory() {
        return category;
    }

    public void setCategory(Map<String, Object> category) {
        this.category = category;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Map<String, Object>> getTags() {
        return tags;
    }

    public void setTags(List<Map<String, Object>> tags) {
        this.tags = tags;
    }
}
