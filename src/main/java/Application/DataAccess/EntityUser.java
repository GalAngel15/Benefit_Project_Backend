package Application.DataAccess;
import org.springframework.beans.factory.annotation.Value;

import Application.business_logic.BoundaryUser;
import Application.business_logic.RoleEnumBoundary;
import Application.business_logic.UserId;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_TBL")
public class EntityUser {

    @Id
    private String id;
    private String email;
    private String userName;
    private RoleEnumEntity role;
    private String avatar;

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EntityUser() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public RoleEnumEntity getRole() {
        return role;
    }

    public void setRole(RoleEnumEntity role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", role=" + role +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public BoundaryUser toBoundary(EntityUser entity)
    {
    	UserId userId  =new UserId();
    	userId.setSuperAPP(get_super_app_name(avatar));
    	userId.setEmail(entity.getEmail());
    	BoundaryUser boun = new BoundaryUser();
    	boun.setUserId(userId);
    	RoleEnumBoundary role =   RoleEnumBoundary.valueOf(entity.getRole().name().toUpperCase());
    	boun.setRole(role);
    	boun.setAvatar(entity.getAvatar());
    	boun.setUserName(entity.getUserName());
    	return boun;
    }
    
	@Value("${spring.application.name:SuperApppp}")
	public String get_super_app_name(String name_super_app) {
		System.err.println("**** reading from configuration default super app name: " + name_super_app);
		return name_super_app;
	}

}
