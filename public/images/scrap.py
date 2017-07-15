# example.py
import pico
from subprocess import call
from pico import PicoApp


@pico.expose()
def scrap():
    call('scrapy crawl /Users/daniel/PycharmProjects/sandbookcrawler/opistacruz -o opistacruz.json')

app = PicoApp()
app.register_module(__name__)
