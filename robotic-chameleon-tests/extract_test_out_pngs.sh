#!/bin/sh

set -ae

if [ -z `which adb` ]; then
  echo "adb must be installed and in PATH"
  exit 1
fi

if [ -z `which avconv` ]; then
  echo "avconv (part of FFMPEG) must be installed and in PATH"
  exit 1
fi

BASE=`dirname $0`
OUT=$BASE/target/test-out-pngs

mkdir -p "$OUT"
adb pull /data/data/fi.gekkio.roboticchameleon.tests/files "$OUT"

convert_to_png() {
  SUFFIX=$1
  PIX_FMT=$2
  for INPUT in `find "$OUT" -type f -name '*.'$SUFFIX`; do
    OUTPUT=${INPUT%.$SUFFIX}.png
    echo "$INPUT -> $OUTPUT"
    avconv -y -v error -xerror -f rawvideo -s 640x480 -pix_fmt "$PIX_FMT" -i "$INPUT" $OUTPUT > /dev/null
    rm "$INPUT"
  done
}

convert_to_png "yuv" "yuv420p"
convert_to_png "NV12" "nv12"
convert_to_png "NV21" "nv21"
