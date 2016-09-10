package linlin.com.optionmenus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menus, menu);
        //添加菜单项，参数１所属组，参数２菜单ID，参数３排序序号，参数４菜单标题
        menu.add(1, 20, 30, "菜单一");
        menu.add(1, 21, 30, "菜单二");
        menu.add(1, 22, 30, "菜单三");
        menu.add(1, 23, 30, "菜单四");
        menu.add(1, 24, 30, "菜单五");
        menu.add(1, 25, 30, "菜单六");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
