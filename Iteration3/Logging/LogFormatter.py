import datetime
import logging
class CustomFormatter(logging.Formatter):
    def format(self, record):
        # Orijinal mesajı alıyoruz
        message = super(CustomFormatter, self).format(record)

        # 'at' yazısını ve tarih/saati ekliyoruz
        log_message = f"{message} at {datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')}"

        return log_message