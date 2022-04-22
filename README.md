# BtnTask

```java
RadioBtnDaily checkBox = findViewById(R.id.cb);
checkBox.setText("Contoh Task On Progres");
checkBox.setTextColor(Color.WHITE);
checkBox.setChackedBtn(new RadioBtnDaily.OnChecked() {
    @Override
    public void pressed(int checked) {
        if (checkBox.kondisi(checked)){
            checkBox.setText(checkBox.TEXT_CHEKED[checked-1]);
        }

        // or
        if (checkBox.isChecked() == checkBox.NANTI){
            // code hare
        }
    }
});
```
