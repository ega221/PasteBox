PasteBox - реализация бэкенд (REST API) для сервиса аналогичного pastebin.com - сервис, позволяющий загружать куски кода или текста и получать на них короткую ссылку, которую можно отправить другим людям.

При загрузке текста ("пасты") пользователь указывает:
1. Срок в течении которого "паста" будет доступна пользователю, после окончания срока хранения, пользователь не сможет получить доступ к тексту, в том числе и автор.
2. Ограничение доступа:
   - public - доступна всем.
   - unlisted - доступна только по ссылке.

Для загруженной "пасты" выдается короткая ссылка вида:
http://pastebox.ru/{hash}
Также пользователи могут получить информацию о последних 10 загруженных "пастах".