package linlin.com.submenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //方式一：通过xml文件加载子菜单
        getMenuInflater().inflate(R.menu.submenus, menu);

        //方式二：代码动态添加子菜单
//        SubMenu file = menu.addSubMenu("文件");
//        SubMenu edit = menu.addSubMenu("编辑");
//
//        file.add(1, 1, 100, "新建");
//        file.add(1, 2, 100, "打开");
//        file.add(1, 3, 100, "关闭");
//
//        edit.add(2, 1, 100, "修改");
//        edit.add(2, 2, 100, "复制");
//        edit.add(2, 3, 100, "粘贴");
//        edit.add(2, 4, 100, "删除");


        getLayoutInflater();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.subitem1_1:
                Toast.makeText(this, "点击了新建", Toast.LENGTH_SHORT).show();
                break;
            case R.id.subitem1_2:
                Toast.makeText(this, "点击了打开", Toast.LENGTH_SHORT).show();
                break;
            case R.id.subitem1_3:
                Toast.makeText(this, "点击了关闭", Toast.LENGTH_SHORT).show();
                break;
            case R.id.subitem2_1:
                Toast.makeText(this, "点击了修改", Toast.LENGTH_SHORT).show();
                break;
            case R.id.subitem2_2:
                Toast.makeText(this, "点击了复制", Toast.LENGTH_SHORT).show();
                break;
            case R.id.subitem2_3:
                Toast.makeText(this, "点击了粘贴", Toast.LENGTH_SHORT).show();
                break;
            case R.id.subitem2_4:
                Toast.makeText(this, "点击了删除", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

//        switch (item.getGroupId()) {
//            case 1:
//                switch (item.getItemId()) {
//                    case 1:
//                        Toast.makeText(this, "点击了新建", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 2:
//                        Toast.makeText(this, "点击了打开", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 3:
//                        Toast.makeText(this, "点击了关闭", Toast.LENGTH_SHORT).show();
//                        break;
//                    default:
//                        break;
//                }
//                break;
//            case 2:
//                switch (item.getItemId()) {
//                    case 1:
//                        Toast.makeText(this, "点击了修改", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 2:
//                        Toast.makeText(this, "点击了复制", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 3:
//                        Toast.makeText(this, "点击了粘贴", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 4:
//                        Toast.makeText(this, "点击了删除", Toast.LENGTH_SHORT).show();
//                        break;
//                    default:
//                        break;
//                }
//                break;
//            default:
//                break;
//        }
        return super.onOptionsItemSelected(item);
    }
}
