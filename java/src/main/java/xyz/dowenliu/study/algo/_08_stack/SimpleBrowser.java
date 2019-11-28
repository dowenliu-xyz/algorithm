package xyz.dowenliu.study.algo._08_stack;

/**
 * 使用前后栈实现浏览器的前进后退
 * <p>create at 2019/11/28</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class SimpleBrowser {
    public static void main(String[] args) {
        SimpleBrowser browser = new SimpleBrowser();
        browser.open("http://www.baidu.com");
        browser.open("http://news.baidu.com");
        browser.open("http://news.baidu.com/ent");
        browser.goBack();
        browser.goBack();
        browser.goForward();
        browser.open("http://www.qq.com");
        browser.goForward();
        browser.goBack();
        browser.goForward();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.checkCurrentPage();
    }

    private String currentPage;
    private LinkedStack<String> backStack;
    private LinkedStack<String> forwardStack;

    public SimpleBrowser() {
        this.backStack = new LinkedStack<>();
        this.forwardStack = new LinkedStack<>();
    }

    public void open(String url) {
        if (this.currentPage != null) {
            this.backStack.push(this.currentPage);
            this.forwardStack.clear();
        }
        showUrl(url, "Open");
    }

    public boolean canGoBack() {
        return !this.backStack.isEmpty();
    }

    public boolean canGoForward() {
        return !this.forwardStack.isEmpty();
    }

    public void goBack() {
        if (this.canGoBack()) {
            this.forwardStack.push(this.currentPage);
            String backUrl = this.backStack.pop();
            showUrl(backUrl, "Back");
            return;
        }

        System.out.println("* Cannot go back, no pages behind.");
    }

    public void goForward() {
        if (this.canGoForward()) {
            this.backStack.push(this.currentPage);
            String forwardUrl = this.forwardStack.pop();
            showUrl(forwardUrl, "Forward");
            return;
        }

        System.out.println("** Cannot go forward, no pages ahead.");
    }

    public void showUrl(String url, String prefix) {
        this.currentPage = url;
        System.out.println(prefix + " page == " + url);
    }

    public void checkCurrentPage() {
        System.out.println("Current page is: " + this.currentPage);
    }
}
