package com.weavus.weavus_bankweb.entity.users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

// 1. UserDetailsインターフェースを実装(implements)します。
public class UsersEntity implements UserDetails {
    private int id;
    private String username;
    private String password;
    private String email;
    private String full_name;
    private LocalDate birth_date;
    private String gender;
    private String postal_code;
    private String prefecture;
    private String city;
    private String address_detail;
    private String phone_number;

    // --- 既存のGetter/Setterはそのままにします ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFull_name() { return full_name; }
    public void setFull_name(String full_name) { this.full_name = full_name; }
    public LocalDate getBirth_date() { return birth_date; }
    public void setBirth_date(LocalDate birth_date) { this.birth_date = birth_date; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getPostal_code() { return postal_code; }
    public void setPostal_code(String postal_code) { this.postal_code = postal_code; }
    public String getPrefecture() { return prefecture; }
    public void setPrefecture(String prefecture) { this.prefecture = prefecture; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getAddress_detail() { return address_detail; }
    public void setAddress_detail(String address_detail) { this.address_detail = address_detail; }
    public String getPhone_number() { return phone_number; }
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }


    // --- 2. UserDetailsインターフェースのメソッドを実装(Override)します ---

    /**
     * ユーザーの権限リストを返します。
     * 管理者機能がないため、すべてのユーザーは "ROLE_USER" 権限を持ちます。
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * ユーザーのパスワードを返します。（DBに保存された暗号化済みのパスワード）
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * ユーザーのIDを返します。（ログイン時に使用する一意の値）
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    // --- 以下はアカウントの状態を示すメソッドです。 ---
    // --- 特別な場合でなければ、すべてtrueを返すように設定します。 ---

    /**
     * アカウントが期限切れでないかどうかを返します。 (true: 期限切れではない)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * アカウントがロックされていないかどうかを返します。 (true: ロックされていない)
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 資格情報（パスワード）が期限切れでないかどうかを返します。 (true: 期限切れではない)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * アカウントが有効であるかどうかを返します。 (true: 有効)
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    // --- UserDetailsと直接関連のない既存のpasswordセッターも維持 ---
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
