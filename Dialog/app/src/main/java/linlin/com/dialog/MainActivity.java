package linlin.com.dialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] sexs = new String[]{"男", "女", "程序员", "女博士"};
    private String[] favorites = new String[]{"唱歌", "跳舞", "弹琴", "睡觉", "逛街", "唱歌", "跳舞", "弹琴", "睡觉", "逛街"};
    private String[] jobs = new String[]{"项目经理", "产品经理", "测试", "美工", "编辑"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                showDialog1();
                break;
            case R.id.button2:
                showDialog2();
                break;
            case R.id.button3:
                showDialog3();
                break;
            case R.id.button4:
                showDialog4();
                break;
            case R.id.button5:
                showDialog5();
                break;
        }
    }

    private void showDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("默认对话框样式");
        builder.setMessage("当前显示的是一般样式的对话框");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("单选对话框");
        builder.setSingleChoiceItems(sexs, 0, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "我选择的性别是"+sexs[i], Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDialog3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("多选对话框");
        builder.setMultiChoiceItems(favorites, null, new DialogInterface.OnMultiChoiceClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                if (b) {
                    Toast.makeText(MainActivity.this, "我喜欢上"+favorites[i], Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "我不喜欢"+favorites[i], Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDialog4() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("列表对话框");
        builder.setItems(jobs, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "您点击了"+favorites[i], Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDialog5() {

    }
}
