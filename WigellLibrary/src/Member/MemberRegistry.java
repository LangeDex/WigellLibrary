package Member;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemberRegistry {
    private final Map<Integer, Member> members = new HashMap<>();

    public void addMember(Member member) { members.put(member.getId(), member); }
    public Member getMember(int id) { return members.get(id); }
    public Collection<Member> getAllMembers() { return members.values(); }

    public boolean updateMemberStatus(int memberId, String newStatus) {
        Member member = members.get(memberId);
        if (member != null) {
            member.setStatus(newStatus);
            System.out.println("Medlem " + member.getName() + " har nu status: " + newStatus);
            return true;
        } else {
            System.out.println("Fel: Ingen medlem med ID " + memberId + " hittades.");
            return false;
        }
    }
}



