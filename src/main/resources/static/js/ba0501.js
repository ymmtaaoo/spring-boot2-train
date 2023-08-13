//BA05削除ボタンのイベント
window.addEventListener('load', function() {

    document.getElementById("delete").addEventListener('click', function() {
        if (confirm("データを削除してもよろしいですか？")) {
            //削除処理実行
        } else {
            //削除処理中断
            alert("キャンセルがクリックされました。");
            return false;
        }
    });
});