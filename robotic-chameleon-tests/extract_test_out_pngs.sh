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

SUFFIXES="rgb yuv"

for SUFFIX in $SUFFIXES; do
  for INPUT in `find "$OUT" -type f -name '*.'$SUFFIX`; do
    OUTPUT=${INPUT%.$SUFFIX}.png
    echo "$INPUT -> $OUTPUT"
    avconv -y -v error -xerror -s 640x480 -pix_fmt bgra -i "$INPUT" $OUTPUT > /dev/null
  done
done
