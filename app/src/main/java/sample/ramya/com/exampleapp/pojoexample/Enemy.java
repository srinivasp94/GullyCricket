package sample.ramya.com.exampleapp.pojoexample;

/**
 * Created by elancer on 7/31/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class Enemy {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avatar_alive")
    @Expose
    private String avatarAlive;
    @SerializedName("avatar_dead")
    @Expose
    private String avatarDead;
//    @SerializedName("battle_messages")
//    @Expose
//    private List<BattleMessage> battleMessages = null;
    @SerializedName("hp")
    @Expose
    private Integer hp;
    @SerializedName("damage_low_range")
    @Expose
    private Integer damageLowRange;
    @SerializedName("damage_high_range")
    @Expose
    private Integer damageHighRange;
    @SerializedName("gold_low_range")
    @Expose
    private Integer goldLowRange;
    @SerializedName("gold_high_range")
    @Expose
    private Integer goldHighRange;
    @SerializedName("xp_low_range")
    @Expose
    private Integer xpLowRange;
    @SerializedName("xp_high_range")
    @Expose
    private Integer xpHighRange;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarAlive() {
        return avatarAlive;
    }

    public void setAvatarAlive(String avatarAlive) {
        this.avatarAlive = avatarAlive;
    }

    public String getAvatarDead() {
        return avatarDead;
    }

    public void setAvatarDead(String avatarDead) {
        this.avatarDead = avatarDead;
    }

//    public List<BattleMessage> getBattleMessages() {
//        return battleMessages;
//    }
//
//    public void setBattleMessages(List<BattleMessage> battleMessages) {
//        this.battleMessages = battleMessages;
//    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getDamageLowRange() {
        return damageLowRange;
    }

    public void setDamageLowRange(Integer damageLowRange) {
        this.damageLowRange = damageLowRange;
    }

    public Integer getDamageHighRange() {
        return damageHighRange;
    }

    public void setDamageHighRange(Integer damageHighRange) {
        this.damageHighRange = damageHighRange;
    }

    public Integer getGoldLowRange() {
        return goldLowRange;
    }

    public void setGoldLowRange(Integer goldLowRange) {
        this.goldLowRange = goldLowRange;
    }

    public Integer getGoldHighRange() {
        return goldHighRange;
    }

    public void setGoldHighRange(Integer goldHighRange) {
        this.goldHighRange = goldHighRange;
    }

    public Integer getXpLowRange() {
        return xpLowRange;
    }

    public void setXpLowRange(Integer xpLowRange) {
        this.xpLowRange = xpLowRange;
    }

    public Integer getXpHighRange() {
        return xpHighRange;
    }

    public void setXpHighRange(Integer xpHighRange) {
        this.xpHighRange = xpHighRange;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
