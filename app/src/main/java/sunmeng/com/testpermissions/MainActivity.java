package sunmeng.com.testpermissions;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Sunmeng Data:2016-07-10
 * E-Mail:Sunmeng1995@outlook.com
 * Description: 6.0
 */

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private Button btn;
    private static final int RC_CAMERA_AND_WIFI = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraTask();
            }
        });
    }


    @AfterPermissionGranted(RC_CAMERA_AND_WIFI)
    public void cameraTask() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.RECORD_AUDIO)) {
            Toast.makeText(this, "去录音", Toast.LENGTH_LONG).show();
        } else {
            EasyPermissions.requestPermissions(this, "申请录音权限", RC_CAMERA_AND_WIFI, Manifest.permission.RECORD_AUDIO);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == RC_CAMERA_AND_WIFI) {
            Log.i("Sunmeng","申请成功");
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == RC_CAMERA_AND_WIFI) {
            Log.i("Sunmeng","申请失败");
        }
        EasyPermissions.checkDeniedPermissionsNeverAskAgain(this,"申请录音权限",R.string.ok,R.string.no, perms);
    }
}
