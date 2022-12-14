package linkedlist;

public class DoubleLinkList {
    public static void main(String[] args) {
        //测试代码
        System.out.println("双线链表的测试");
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(3, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(5, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(8, "林冲", "豹子头");
        //创建一个双线链表对象
        DoubleLinkedList doubleLinkedList =new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(8, "公孙圣", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();
        //删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况");
        doubleLinkedList.list();
        //按顺序添加
        HeroNode2 orderHerNode = new HeroNode2(2, "公孙圣", "入云龙");
        doubleLinkedList.addByOrder(orderHerNode);
        System.out.println("顺序添加的链表情况");
        doubleLinkedList.list();
    }


}


//创建一个双向链表的类
class DoubleLinkedList {
    //先初始化 一个头节点 ，头节点不动 不存放具体内容
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //顺序添加
    public void addByOrder(HeroNode2 heroNode){
        HeroNode2 temp=head;
        while (true){
            if (temp.next==null){
                //说明temp已经在链表最后
                break;
            }
            if (temp.next.no> heroNode.no) {
                heroNode.next=temp.next;
                temp.next=heroNode;
                heroNode.pre=temp;
                break;
            }else if (temp.no==temp.next.no){
                System.out.println("该序号已经存在,无法天机");
                break;
            }
            temp=temp.next;//后移
        }

    }

    //添加一个节点 到双向链表的最后
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此我们需要一个辅助变量 temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp指向了链表的最后
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改一个节点的内容 可以看到双向链表的节点内容修改和单向链表一样
    //只是 节点类型改成HeroNode2
    public void update(HeroNode2 newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {//没有找到
            System.out.printf("没有找到 编号%d 的节点,不能修改\n", newHeroNode.no);
        }
    }

    //从双向链表删除一个节点
    //说明
    //1.对于双向链表 我们可以直接找到要删除的这个节点
    //2.找到以后 自我删除即可
    public void del(int no) {
        //判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head;
        boolean flag = false;//标识 是否找到待删除节点的前一个节点
        while (true) {
            if (temp == null) {//已经到链表的最后
                break;
            }
            if (temp.no == no) {
                //找到了 待删除节点的前一个节点temp
                flag = true;//表示找到了
                break;
            }
            temp = temp.next;//temp后移
        }
        if (flag) {//找到
            //可以删除
//            temp.next = temp.next.next;[单向链表]
            //让要删除节点temp的前一个节点 指向temp的下一个节点
            temp.pre.next = temp.next;
            //让要删除节点temp的后一个节点 指向temp的前一个节点
            //如果是最后一个节点 就不需要执行下面这句话 否则出现空指针异常 temp.next.pre=temp.pre;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的%d 节点，不存在\n", no);
        }
    }

    //遍历双向链表的方法
    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点,不能动,因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将next后移
            temp = temp.next;
        }

    }
}

//定义一个HeroNode,每个HeroNode 对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点 默认为Null
    public HeroNode2 pre;//指向前一个节点 默认为Null

    //构造器
    public HeroNode2(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

}