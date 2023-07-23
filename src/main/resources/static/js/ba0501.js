window.onload = function () {
    document.getElementById('delete').onclick = function() {
        if (confirm("データを削除してもよろしいですか？")) {
            //削除処理実行
        } else {
            //削除処理中断
            alert("キャンセルがクリックされました。");
            return false;
        }
    };
};